package example.domain.resources;

import com.google.common.base.Optional;
import example.domain.Document;
import example.domain.DocumentRepository;
import example.domain.Identity;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static example.domain.Identity.fromValue;
import static example.domain.resources.Resources.json;
import static example.domain.resources.Resources.notFound;
import static example.domain.resources.Resources.uriFor;
import static org.json.simple.JSONValue.parse;

@Path("/form/{documentId}")
public class DocumentResource {

    private final DocumentRepository repository;

    @Inject
    public DocumentResource(DocumentRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response display(@Context UriInfo uriInfo, @PathParam("documentId") String documentId) {
        Identity id = fromValue(documentId);
        Optional<Document> option = repository.get(id);
        if (option.isPresent()) {
            return render(uriInfo, id, option.get());
        }
        return notFound();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context UriInfo uriInfo, @PathParam("documentId") String documentId, String content) {
        Optional<Document> option = repository.get(fromValue(documentId));
        if (option.isPresent()) {
            Document updated = update(option.get(), content);
            return render(uriInfo, updated.getId(), updated);
        }
        return notFound();
    }

    private Response render(UriInfo uriInfo, Identity id, Document document) {
        Map<String, String> actions = newLinkedHashMap();
        actions.put("update", uriFor(uriInfo, DocumentResource.class, id));
        actions.put("index", uriFor(uriInfo, IndexResource.class));

        Map<String, Object> content = newLinkedHashMap();
        content.put("document", document.toMap());
        content.put("actions", actions);

        return json(content);
    }

    private Document update(Document document, String content) {
        JSONObject data = (JSONObject) parse(content);
        Optional<String> value = fromNullable((String) data.get("value"));
        Document updated = document.update(value);
        repository.set(updated);
        return updated;
    }
}
