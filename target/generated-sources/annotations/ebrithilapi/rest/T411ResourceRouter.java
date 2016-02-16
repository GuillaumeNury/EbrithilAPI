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

public class T411ResourceRouter extends RestxRouter {

    public T411ResourceRouter(
                    final T411Resource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "T411ResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.util.List<ebrithilapi.areas.t411.model.Torrent>>("default#T411Resource#topFilmsOfTheWeek",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.util.List<ebrithilapi.areas.t411.model.Torrent>>build(Types.newParameterizedType(java.util.List.class, ebrithilapi.areas.t411.model.Torrent.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/t411/films/week"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.util.List<ebrithilapi.areas.t411.model.Torrent>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.topFilmsOfTheWeek(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Torrent>";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "";
                operation.sourceLocation = "ebrithilapi.rest.T411Resource#public List<ebrithilapi.areas.t411.model.Torrent> topFilmsOfTheWeek() throws org.apache.http.ParseException, java.io.IOException";
            }
        },
        new StdEntityRoute<Void, java.util.List<ebrithilapi.areas.t411.model.TorrentRecap>>("default#T411Resource#autoFilmsOfTheWeek",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.util.List<ebrithilapi.areas.t411.model.TorrentRecap>>build(Types.newParameterizedType(java.util.List.class, ebrithilapi.areas.t411.model.TorrentRecap.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/t411/films/week/auto"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.util.List<ebrithilapi.areas.t411.model.TorrentRecap>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.autoFilmsOfTheWeek(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "TorrentRecap>";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "";
                operation.sourceLocation = "ebrithilapi.rest.T411Resource#public List<ebrithilapi.areas.t411.model.TorrentRecap> autoFilmsOfTheWeek() throws org.apache.http.ParseException, java.io.IOException";
            }
        },
        });
    }

}
