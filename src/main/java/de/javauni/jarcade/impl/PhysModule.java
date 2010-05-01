package de.javauni.jarcade.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

import com.google.inject.assistedinject.FactoryProvider;

import de.javauni.jarcade.impl.phys.PhysicalOperator;

import de.javauni.jarcade.model.scene.ZeroLayer;
import de.javauni.jarcade.model.scene.operate.LayerOperatorFactory;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;

public class PhysModule extends AbstractModule {
	@Override
	protected void configure() {
        bind(World.class).toProvider(new Provider<World>() {
			@Override
			public World get() {
				return new World(new Vector2f(0,9.81f), 5);
			}});
        bind(new TypeLiteral<LayerOperatorFactory<PhysicalOperator>>(){})
            .annotatedWith(ZeroLayer.class)
            .toProvider(FactoryProvider.newFactory(
                new TypeLiteral<LayerOperatorFactory<PhysicalOperator>>(){},
                new TypeLiteral<PhysicalOperator>(){}));
	}
}
