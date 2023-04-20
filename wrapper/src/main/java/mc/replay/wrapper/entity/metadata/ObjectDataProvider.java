package mc.replay.wrapper.entity.metadata;

public interface ObjectDataProvider {

    int getObjectData();

    boolean requiresVelocityPacketAtSpawn();
}