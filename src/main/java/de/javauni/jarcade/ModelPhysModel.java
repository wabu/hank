package de.javauni.jarcade;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import com.google.inject.assistedinject.FactoryProvider;

import de.javauni.jarcade.model.phys.PhysicalOperator;
import de.javauni.jarcade.model.scene.ZeroLayer;
import de.javauni.jarcade.model.scene.operate.LayerOperatorFactory;

public class ModelPhysModel extends AbstractModule {
	@Override
	protected void configure() {
        bind(new TypeLiteral<LayerOperatorFactory<PhysicalOperator>>(){})
            .annotatedWith(ZeroLayer.class)
            .toProvider(FactoryProvider.newFactory(
                new TypeLiteral<LayerOperatorFactory<PhysicalOperator>>(){},
                new TypeLiteral<PhysicalOperator>(){}));
	}
}
