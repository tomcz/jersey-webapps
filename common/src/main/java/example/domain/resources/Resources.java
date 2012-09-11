package example.domain.resources;

import com.google.common.collect.ImmutableMap;
import org.json.simple.JSONValue;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Map;

public class Resources {

    public static String linkFor(UriInfo uriInfo, Class resource, Object... pathParams) {
        return uriFor(uriInfo, resource, pathParams).toASCIIString();
    }

    public static URI uriFor(UriInfo uriInfo, Class resource, Object... pathParams) {
        return uriInfo.getBaseUriBuilder().path(resource).build(pathParams);
    }

    public static Response json(Map content) {
        return okNoCache(JSONValue.toJSONString(content));
    }

    public static Response okNoCache(String content) {
        return Response.ok(content).cacheControl(noCache()).build();
    }

    public static CacheControl noCache() {
        CacheControl control = new CacheControl();
        control.setNoCache(true);
        control.setNoStore(true);
        control.setMaxAge(0);
        return control;
    }

    public static Response notFound() {
        return Response.status(Status.NOT_FOUND).build();
    }

    public static Map<String, String> action(Class<? extends Annotation> action, URI uri) {
        return ImmutableMap.of("uri", uri.toASCIIString(), "method", action.getSimpleName());
    }
}
