public class Trap {
    private final int trapID;
    private boolean activated;
    private final String activeDescription;
    private final String discoveryDescription;
    private final int trapEffects;

    public Trap(int trapID, String activationDescription, String discoveryDescription, int trapEffects) {
        this.trapID = trapID;
        this.activated = false;
        this.activeDescription = activationDescription;
        this.discoveryDescription = discoveryDescription;
        this.trapEffects = trapEffects;
    }

    public int trapActivate() {
        this.activated = true;
        return this.trapEffects;
    }

    public int getTrapEffects() {
        return trapEffects;
    }

    public String getActiveDescription() {
        return activeDescription;
    }

    public String getDiscoveryDescription() {
        return discoveryDescription;
    }

    public boolean isActivated() {
        return activated;
    }

    public int getTrapID() {
        return trapID;
    }

    public static class TrapCreator {

        public static Trap createTrap(int id) {
            switch (id) {
                case 0://Spike Trap
                    return new Trap(id,
                            "Spikes suddenly erupt from the ground!"
                            , "A small tripwire leads to a set of spikes, primed to launch.",
                            5);
                case 1://Log Trap
                    return new Trap(id,
                            "A series of large heavy logs roll towards you, threatening to bowl you over",
                            "A container of heavy logs threatens to be unleashed if one steps on the wrong panel.",
                            8);
            }
            return null;
        }
    }
}