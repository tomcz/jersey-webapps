package example.domain.resources;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import example.domain.DocumentRepository;
import example.domain.Identity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static example.domain.resources.Resources.okNoCache;
import static example.domain.resources.Resources.uriFor;
import static org.json.simple.JSONValue.toJSONString;

@Path("/index")
public class IndexResource {

    private final DocumentRepository repository;

    @Inject
    public IndexResource(DocumentRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@Context UriInfo uriInfo) {
        Map<String, Object> map = newLinkedHashMap();
        map.put("newURL", uriFor(uriInfo, DocumentResource.class, Identity.NEW));
        map.put("documents", links(uriInfo, repository.getIDs()));
        return okNoCache(toJSONString(map));
    }

    private List<Map<String, String>> links(final UriInfo uriInfo, List<Identity> ids) {
        return transform(ids, new Function<Identity, Map<String, String>>() {
            public Map<String, String> apply(Identity id) {
                return ImmutableMap.of("id", id.toString(), "url", uriFor(uriInfo, DocumentResource.class, id));
            }
        });
    }
}
