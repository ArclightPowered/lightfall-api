package io.izzel.lightfall.api.event;

import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.protocol.packet.LoginPayloadRequest;
import net.md_5.bungee.protocol.packet.LoginPayloadResponse;

public abstract class ModernFMLHandshakeEvent extends Event implements Cancellable {

    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public static class Request extends ModernFMLHandshakeEvent {

        private final LoginPayloadRequest request;

        public Request(LoginPayloadRequest request) {
            this.request = request;
        }

        public LoginPayloadRequest getRequest() {
            return request;
        }
    }

    public static class Response extends ModernFMLHandshakeEvent {

        private final LoginPayloadResponse response;

        public Response(LoginPayloadResponse response) {
            this.response = response;
        }

        public LoginPayloadResponse getResponse() {
            return response;
        }
    }
}
