package ebrithilapi.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

import restx.common.Types;
import restx.*;
import restx.entity.*;
import restx.http.*;
import restx.factory.*;
import restx.security.*;
import static restx.security.Permissions.*;
import restx.description.*;
import restx.converters.MainStringConverter;
import static restx.common.MorePreconditions.checkPresent;

import javax.validation.Validator;
import static restx.validation.Validations.checkValid;

import java.io.IOException;
import java.io.PrintWriter;

@Component(priority = 0)

public class RatpResourceRouter extends RestxRouter {

    public RatpResourceRouter(
                    final RatpResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "RatpResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.util.ArrayList<ebrithilapi.areas.ratp.model.TimeTableLight>>("default#RatpResource#getNextBus",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.util.ArrayList<ebrithilapi.areas.ratp.model.TimeTableLight>>build(Types.newParameterizedType(java.util.ArrayList.class, ebrithilapi.areas.ratp.model.TimeTableLight.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/ratp/nextbus"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.util.ArrayList<ebrithilapi.areas.ratp.model.TimeTableLight>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getNextBus(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "TimeTableLight>";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "";
                operation.sourceLocation = "ebrithilapi.rest.RatpResource#public ArrayList<ebrithilapi.areas.ratp.model.TimeTableLight> getNextBus() ";
            }
        },
        });
    }

}
