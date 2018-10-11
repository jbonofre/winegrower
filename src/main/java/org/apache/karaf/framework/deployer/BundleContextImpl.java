/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.framework.deployer;

import static java.util.Optional.ofNullable;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Dictionary;
import java.util.function.Supplier;
import java.util.jar.Manifest;

import org.apache.karaf.framework.service.BundleRegistry;
import org.apache.karaf.framework.service.OSGiServices;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class BundleContextImpl implements BundleContext {
    private final Manifest manifest;
    private final OSGiServices services;
    private final Supplier<Bundle> bundleSupplier;
    private final BundleRegistry registry;

    BundleContextImpl(final Manifest manifest, final OSGiServices services, final Supplier<Bundle> bundleSupplier,
                      final BundleRegistry registry) {
        this.manifest = manifest;
        this.services = services;
        this.bundleSupplier = bundleSupplier;
        this.registry = registry;
    }

    public Manifest getManifest() {
        return manifest;
    }

    @Override
    public String getProperty(final String key) {
        return null;
    }

    @Override
    public Bundle getBundle() {
        return bundleSupplier.get();
    }

    @Override
    public Bundle installBundle(final String location, final InputStream input) throws BundleException {
        throw new BundleException("Unsupported operation");
    }

    @Override
    public Bundle installBundle(final String location) throws BundleException {
        throw new BundleException("Unsupported operation");
    }

    @Override
    public Bundle getBundle(final long id) {
        return ofNullable(registry.getBundles().get(id)).map(OSGiBundleLifecycle::getBundle).orElse(null);
    }

    @Override
    public Bundle[] getBundles() {
        return registry.getBundles().values().stream().map(OSGiBundleLifecycle::getBundle).toArray(Bundle[]::new);
    }

    @Override
    public void addServiceListener(final ServiceListener listener, final String filter) {
        services.addListener(listener, filter);
    }

    @Override
    public void addServiceListener(final ServiceListener listener) {
        addServiceListener(listener, null);
    }

    @Override
    public void removeServiceListener(final ServiceListener listener) {
        services.removeListener(listener);
    }

    @Override
    public void addBundleListener(final BundleListener listener) {

    }

    @Override
    public void removeBundleListener(final BundleListener listener) {

    }

    @Override
    public void addFrameworkListener(final FrameworkListener listener) {

    }

    @Override
    public void removeFrameworkListener(final FrameworkListener listener) {

    }

    @Override
    public ServiceRegistration<?> registerService(final String[] clazzes, final Object service, final Dictionary<String, ?> properties) {
        return null;
    }

    @Override
    public ServiceRegistration<?> registerService(final String clazz, final Object service, final Dictionary<String, ?> properties) {
        return null;
    }

    @Override
    public <S> ServiceRegistration<S> registerService(final Class<S> clazz, final S service, final Dictionary<String, ?> properties) {
        return null;
    }

    @Override
    public <S> ServiceRegistration<S> registerService(final Class<S> clazz, final ServiceFactory<S> factory, final Dictionary<String, ?> properties) {
        return null;
    }

    @Override
    public ServiceReference<?>[] getServiceReferences(final String clazz, final String filter) throws InvalidSyntaxException {
        return new ServiceReference[0];
    }

    @Override
    public ServiceReference<?>[] getAllServiceReferences(final String clazz, final String filter) throws InvalidSyntaxException {
        return new ServiceReference[0];
    }

    @Override
    public ServiceReference<?> getServiceReference(final String clazz) {
        return null;
    }

    @Override
    public <S> ServiceReference<S> getServiceReference(final Class<S> clazz) {
        return null;
    }

    @Override
    public <S> Collection<ServiceReference<S>> getServiceReferences(final Class<S> clazz, final String filter)
            throws InvalidSyntaxException {
        return null;
    }

    @Override
    public <S> S getService(final ServiceReference<S> reference) {
        return null;
    }

    @Override
    public boolean ungetService(final ServiceReference<?> reference) {
        return false;
    }

    @Override
    public <S> ServiceObjects<S> getServiceObjects(final ServiceReference<S> reference) {
        return null;
    }

    @Override
    public File getDataFile(final String filename) {
        return null;
    }

    @Override
    public Filter createFilter(final String filter) throws InvalidSyntaxException {
        return null;
    }

    @Override
    public Bundle getBundle(final String location) {
        return bundleSupplier.get();
    }
}