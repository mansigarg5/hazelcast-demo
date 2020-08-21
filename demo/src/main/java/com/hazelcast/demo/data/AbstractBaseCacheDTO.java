package com.hazelcast.demo.data;

import java.io.Serializable;

/**
 * The Abstract base cache dto for all dto.
 * It forces implementation of {@link #toString()}
 * Also applies serialization.
 */
public abstract class AbstractBaseCacheDTO implements Serializable {

}
