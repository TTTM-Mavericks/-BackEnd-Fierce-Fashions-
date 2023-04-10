package com.ff.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Converter(autoApply = true)
public class ByteArrayToBlobConverter implements AttributeConverter<byte[], Blob> {

    @Override
    public Blob convertToDatabaseColumn(byte[] bytes) {
        try {
            return new SerialBlob(bytes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] convertToEntityAttribute(Blob blob) {
        try {
            return blob.getBytes(1, (int) blob.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}