package ebrithilapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import ebrithilapi.rest.RatpResource;

@Machine
public class RatpResourceFactoryMachine extends SingleNameFactoryMachine<ebrithilapi.rest.RatpResource> {
    public static final Name<ebrithilapi.rest.RatpResource> NAME = Name.of(ebrithilapi.rest.RatpResource.class, "RatpResource");

    public RatpResourceFactoryMachine() {
        super(0, new StdMachineEngine<ebrithilapi.rest.RatpResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected ebrithilapi.rest.RatpResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new RatpResource(

                );
            }
        });
    }

}
