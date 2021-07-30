package io.quarkus.kafka.client.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class JsonbDeserializerTest {
    @Test
    void shouldDeserializeEntity() {
        MyEntity expected = new MyEntity(1, "entity1");
        JsonbDeserializer<MyEntity> deserializer = new JsonbDeserializer<>(MyEntity.class);
        MyEntity actual = deserializer.deserialize("topic", "{\"id\":1,\"name\":\"entity1\"}".getBytes());
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeserializeNullAsNullString() {
        JsonbDeserializer<MyEntity> deserializer = new JsonbDeserializer<>(MyEntity.class);
        MyEntity results = deserializer.deserialize("topic", "null".getBytes());
        assertNull(results);
    }

    @Test
    void shouldDeserializeNullAsNull() {
        JsonbDeserializer<MyEntity> deserializer = new JsonbDeserializer<>(MyEntity.class);
        MyEntity results = deserializer.deserialize("topic", null);
        assertNull(results);
    }
}
