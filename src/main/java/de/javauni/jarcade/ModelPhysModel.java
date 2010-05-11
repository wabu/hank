package de.javauni.jarcade;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import com.google.inject.assistedinject.FactoryProvider;

import de.javauni.jarcade.model.phys.ControlableBody;
import de.javauni.jarcade.model.phys.ControlableBodyOperator;
import de.javauni.jarcade.model.phys.PhysicalOperator;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.ZeroLayer;

import de.javauni.jarcade.model.scene.operate.OperatorFactory;

public class ModelPhysModel extends AbstractModule {
	@Override
	protected void configure() {
        bind(new TypeLiteral<OperatorFactory<Layer,PhysicalOperator>>(){})
            .annotatedWith(ZeroLayer.class)
            .toProvider(FactoryProvider.newFactory(
                new TypeLiteral<OperatorFactory<Layer,PhysicalOperator>>(){},
                new TypeLiteral<PhysicalOperator>(){}));

        bind(new TypeLiteral<OperatorFactory<ControlableBody,ControlableBodyOperator>>(){})
            .annotatedWith(ZeroLayer.class)
            .toProvider(FactoryProvider.newFactory(
                new TypeLiteral<OperatorFactory<ControlableBody,ControlableBodyOperator>>(){},
                new TypeLiteral<ControlableBodyOperator>(){}));
	}
}
