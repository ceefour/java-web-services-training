package com.hendyirawan.jws1033;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper {
    @Override
    public Response toResponse(Throwable exception) {
        return Response.serverError().entity(exception.getMessage()).build();
    }
}