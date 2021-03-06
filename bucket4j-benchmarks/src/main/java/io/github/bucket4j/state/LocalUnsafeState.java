/*
 *
 * Copyright 2015-2018 Vladimir Bukhtoyarov
 *
 *       Licensed under the Apache License, Version 2.0 (the "License");
 *       you may not use this file except in compliance with the License.
 *       You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.github.bucket4j.state;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.local.SynchronizationStrategy;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.time.Duration;

@State(Scope.Benchmark)
public class LocalUnsafeState {

    public final Bucket bucket = Bucket4j.builder()
            .withMillisecondPrecision()
            .addLimit(
                    Bandwidth.simple(Long.MAX_VALUE / 2, Duration.ofNanos(Long.MAX_VALUE / 2))
            )
            .withSynchronizationStrategy(SynchronizationStrategy.NONE)
            .build();


    public final Bucket bucketWithoutRefill = Bucket4j.builder()
            .withMillisecondPrecision()
            .withCustomTimePrecision(() -> 0)
            .addLimit(
                    Bandwidth.simple(Long.MAX_VALUE / 2, Duration.ofNanos(Long.MAX_VALUE / 2))
            )
            .withSynchronizationStrategy(SynchronizationStrategy.NONE)
            .build();


}
