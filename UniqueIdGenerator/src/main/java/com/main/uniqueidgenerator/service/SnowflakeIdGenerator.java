package com.main.uniqueidgenerator.service;

public class SnowflakeIdGenerator {
    private final long machineId;
    private final long dataCenterId;
    private final long customEpoch = 1609459200000L;
    private long sequence=0L;
    private long lastTimeStamp=-1L;
    // Bit allocations

    private final int machineIdBits=5;
    private final int dataCentreBits=5;
    private final int sequenceIdBits=12;
    private final long maxMachineId=~(-1L<<machineIdBits);
    private final long maxDataCenterId=~(-1L<<dataCentreBits);
    private final long maxSequenceId=~(-1L<<sequenceIdBits);
    private final int machineIdShift=sequenceIdBits;
    private final int dataCenterIdShift=machineIdShift+machineIdBits;
    private final int timeStampShift=dataCenterIdShift+dataCentreBits;
    public SnowflakeIdGenerator(long machineId,long dataCenterId){
        this.machineId=machineId;
        this.dataCenterId=dataCenterId;
    }

    public synchronized long nextId(){
        long currentTimeStamp = System.currentTimeMillis();
        if(currentTimeStamp<lastTimeStamp){
            throw new RuntimeException("Clock can't move backward");
        }
        if(currentTimeStamp==lastTimeStamp){
            sequence=(sequence+1)&maxSequenceId;
            if(sequence==0){
                currentTimeStamp=waitTillNextTimeStamp(currentTimeStamp);
            }
        }else{
            sequence=0L;
        }
        lastTimeStamp=currentTimeStamp;
        return ((currentTimeStamp-customEpoch)<<timeStampShift)|(dataCenterId<<dataCenterIdShift)|(machineId<<machineIdShift)|(sequence);
    }

    private long waitTillNextTimeStamp(long currentTimeStamp) {
        if(currentTimeStamp<=lastTimeStamp){
            currentTimeStamp=System.currentTimeMillis();
        }
        return currentTimeStamp;
    }
}
