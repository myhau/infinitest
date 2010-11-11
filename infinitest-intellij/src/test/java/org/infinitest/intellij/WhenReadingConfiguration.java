package org.infinitest.intellij;

import static org.infinitest.intellij.idea.facet.ConfigurationElementBuilder.*;
import static org.infinitest.keys.SampleKeys.*;
import static org.junit.Assert.*;

import org.infinitest.intellij.idea.facet.InfinitestFacetConfiguration;
import org.junit.Before;
import org.junit.Test;

import com.intellij.openapi.util.InvalidDataException;

public class WhenReadingConfiguration
{
    private InfinitestFacetConfiguration configuration;

    @Before
    public void inContext()
    {
        configuration = new InfinitestFacetConfiguration();
    }

    @Test
    public void shouldReadScmEnabledSetting() throws InvalidDataException
    {
        configuration.readExternal(configuration().withScmUpdate(true).build());
        assertTrue(configuration.isScmUpdateEnabled());
    }

    @Test
    public void shouldShouldReadScmDisabledSettings() throws InvalidDataException
    {
        configuration.readExternal(configuration().withScmUpdate(false).build());
        assertFalse(configuration.isScmUpdateEnabled());
    }

    @Test
    public void shouldTreatScmAsDisabledIfNotDefined() throws InvalidDataException
    {
        configuration.readExternal(configuration().build());
        assertFalse(configuration.isScmUpdateEnabled());
    }

    @Test
    public void shouldReadLicenseKey() throws InvalidDataException
    {
        configuration.readExternal(configuration().withLicenseKey(VALID_KEY).build());
        assertEquals("Corporate Use License", configuration.getLicense().getLicenseName());
    }

    @Test
    public void shouldReadIndividualLicense() throws InvalidDataException
    {
        configuration.readExternal(configuration().withLicenseKey(INDIVIDUAL_LICENSE).build());
        assertEquals("Individual Use License", configuration.getLicense().getLicenseName());
    }

    @Test
    public void shouldTreatEmptyLicenseAsTrial() throws InvalidDataException
    {
        configuration.readExternal(configuration().build());
        assertEquals("30 Day Trial License", configuration.getLicense().getLicenseName());
    }
}