package ebrithilapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import ebrithilapi.rest.HelloResourceRouter;

@Machine
public class HelloResourceRouterFactoryMachine extends SingleNameFactoryMachine<ebrithilapi.rest.HelloResourceRouter> {
    public static final Name<ebrithilapi.rest.HelloResourceRouter> NAME = Name.of(ebrithilapi.rest.HelloResourceRouter.class, "HelloResourceRouter");

    public HelloResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<ebrithilapi.rest.HelloResourceRouter>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<ebrithilapi.rest.HelloResource> resource = Factory.Query.byClass(ebrithilapi.rest.HelloResource.class).mandatory();
private final Factory.Query<restx.entity.EntityRequestBodyReaderRegistry> readerRegistry = Factory.Query.byClass(restx.entity.EntityRequestBodyReaderRegistry.class).mandatory();
private final Factory.Query<restx.entity.EntityResponseWriterRegistry> writerRegistry = Factory.Query.byClass(restx.entity.EntityResponseWriterRegistry.class).mandatory();
private final Factory.Query<restx.converters.MainStringConverter> converter = Factory.Query.byClass(restx.converters.MainStringConverter.class).mandatory();
private final Factory.Query<javax.validation.Validator> validator = Factory.Query.byClass(javax.validation.Validator.class).optional();
private final Factory.Query<restx.security.RestxSecurityManager> securityManager = Factory.Query.byClass(restx.security.RestxSecurityManager.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
resource,
readerRegistry,
writerRegistry,
converter,
validator,
securityManager
                ));
            }

            @Override
            protected ebrithilapi.rest.HelloResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new HelloResourceRouter(
satisfiedBOM.getOne(resource).get().getComponent(),
satisfiedBOM.getOne(readerRegistry).get().getComponent(),
satisfiedBOM.getOne(writerRegistry).get().getComponent(),
satisfiedBOM.getOne(converter).get().getComponent(),
satisfiedBOM.getOneAsComponent(validator),
satisfiedBOM.getOne(securityManager).get().getComponent()
                );
            }
        });
    }

}
