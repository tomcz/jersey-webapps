package example.domain.resources;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

public class Resources {

    public static String uriFor(UriInfo uriInfo, Class resource, Object... pathParams) {
        return uriInfo.getBaseUriBuilder().path(resource).build(pathParams).toASCIIString();
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
}
