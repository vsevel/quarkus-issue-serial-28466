package io.quarkiverse.myext.deployment;

import java.util.AbstractSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class MyextProcessor {

    private static final Logger log = LoggerFactory.getLogger(MyextProcessor.class);

    private static final String FEATURE = "myext";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    void reflection(BuildProducer<ReflectiveClassBuildItem> reflection) {
        log.info("configuring reflection");
        reflection.produce(new ReflectiveClassBuildItem(true, false, false, false, AbstractSet.class.getName()));
        reflection.produce(new ReflectiveClassBuildItem(true, false, false, true, "java.util.Collections$SingletonSet"));
        reflection.produce(new ReflectiveClassBuildItem(true, false, false, true, "java.util.ImmutableCollections$Set12"));
    }
}
