package com.hyxiao.netty.v2.marshalling;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodeCFactory {

    public static MarshallingDecoder buildMarshallingDecoder() {

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        DefaultUnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        return new MarshallingDecoder(provider, 1024 * 1024);
    }


    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        DefaultMarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);

        return new MarshallingEncoder(provider);
    }

}
