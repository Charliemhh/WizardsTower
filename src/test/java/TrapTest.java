import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    Trap spikeTrap = Trap.TrapCreator.createTrap(0);
    @Test
    void canCreateTrap(){
        assert spikeTrap != null;
        assertEquals("Spikes suddenly erupt from the ground!",spikeTrap.getActiveDescription());
    }
    @Test
    void canActivateTrap(){
        spikeTrap.trapActivate();
        assertTrue(spikeTrap.isActivated());
    }
    @Test
    void trapEffectsReturnCorrectly(){
        assertEquals(5,spikeTrap.trapActivate());
    }
    @Test
    void canGetDiscoveryDescription(){
        assertEquals("A small tripwire leads to a set of spikes, primed to launch."
                ,spikeTrap.getDiscoveryDescription());
    }
}