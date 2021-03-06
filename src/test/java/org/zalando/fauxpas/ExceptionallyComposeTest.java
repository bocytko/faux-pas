package org.zalando.fauxpas;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.zalando.fauxpas.FauxPas.exceptionallyCompose;
import static org.zalando.fauxpas.FauxPas.partially;

class ExceptionallyComposeTest {

    @Test
    void shouldExceptionallyCompose() {
        final CompletableFuture<String> original = new CompletableFuture<>();
        final CompletableFuture<String> unit = exceptionallyCompose(original, partially(e ->
                completedFuture("result")));

        original.completeExceptionally(new RuntimeException());

        assertThat(unit).isCompletedWithValue("result");
    }

}
