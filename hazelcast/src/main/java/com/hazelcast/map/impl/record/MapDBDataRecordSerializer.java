package com.hazelcast.map.impl.record;

import org.mapdb.Serializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public  class MapDBDataRecordSerializer implements Serializable, Serializer<Record> {

    final static MapDBDataSerializer ds = new MapDBDataSerializer();


    @Override
    public void serialize(DataOutput out, Record value) throws IOException {
        DataRecordWithStats w = (DataRecordWithStats) value;
        ds.serialize(out,w.getKey());
        ds.serialize(out,w.getValue());
    }

    @Override
    public Record deserialize(DataInput in, int available) throws IOException {
        DataRecordWithStats record = new DataRecordWithStats();
        record.setKey(ds.deserialize(in,-1));
        record.setValue(ds.deserialize(in,-1));
        return record;
    }

    @Override
    public int fixedSize() {
        return -1;
    }
}
