package example.domain.resources;

import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.google.common.collect.Maps.newLinkedHashMap;
import static example.domain.resources.Resources.okNoCache;
import static org.json.simple.JSONValue.toJSONString;

@Path("/status")
public class StatusResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response status() {
        String now = new DateTime().toString();

        Map<String, String> map = newLinkedHashMap();
        map.put("status", "OK");
        map.put("asOf", now);

        return okNoCache(toJSONString(map));
    }
}
