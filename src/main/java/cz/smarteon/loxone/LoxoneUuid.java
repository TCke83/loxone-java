package cz.smarteon.loxone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;

import java.nio.ByteBuffer;
import java.util.Objects;

import static cz.smarteon.loxone.Codec.*;

/**
 * Represents a uuid specific for Loxone.
 */
@EqualsAndHashCode
public final class LoxoneUuid {

    private final String raw;
    private final long id1;
    private final int id2;
    private final int id3;
    private final byte[] id4;

    public LoxoneUuid(long id1, int id2, int id3, byte[] id4) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.id4 = id4;
        this.raw = toUnsignedIntHex(id1) + "-"
                + toUnsignedShortHex(id2) + "-"
                + toUnsignedShortHex(id3) + "-"
                + bytesToHex(id4);
    }

    @JsonCreator
    public LoxoneUuid(String value) {
        this.raw = Objects.requireNonNull(value);
        final String[] parts = value.split("-");
        long tid1 = 0;
        int tid2 = 0;
        int tid3 = 0;
        byte[] tid4 = null;
        if (parts.length == 4) {
            try {
                tid1 = readUnsingedInt(ByteBuffer.wrap(hexToBytes(parts[0])));
                tid2 = readUnsignedShort(ByteBuffer.wrap(hexToBytes(parts[1])));
                tid3 = readUnsignedShort(ByteBuffer.wrap(hexToBytes(parts[2])));
                tid4 = hexToBytes(parts[3]);
            } catch (Exception e) {
                // probably not a standard UUID
            }
        }
        this.id1 = tid1;
        this.id2 = tid2;
        this.id3 = tid3;
        this.id4 = tid4;
    }

    @Override
    @JsonValue
    public String toString() {
        return raw;
    }

}
