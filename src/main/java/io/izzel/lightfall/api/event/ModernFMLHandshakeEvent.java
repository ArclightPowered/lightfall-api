package io.izzel.lightfall.api.event;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.protocol.packet.LoginPayloadRequest;
import net.md_5.bungee.protocol.packet.LoginPayloadResponse;

public abstract class ModernFMLHandshakeEvent extends Event implements Cancellable {

    private final ProxiedPlayer player;
    private final ServerInfo serverInfo;
    private boolean cancelled = false;

    protected ModernFMLHandshakeEvent(ProxiedPlayer player, ServerInfo serverInfo) {
        this.player = player;
        this.serverInfo = serverInfo;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public ServerInfo getTarget() {
        return serverInfo;
    }

    public static class Request extends ModernFMLHandshakeEvent {

        private final LoginPayloadRequest request;
        private LoginPayloadResponse response;

        public Request(ProxiedPlayer player, ServerInfo serverInfo, LoginPayloadRequest request) {
            super(player, serverInfo);
            this.request = request;
        }

        public LoginPayloadRequest getRequest() {
            return request;
        }

        public void setReply(LoginPayloadResponse response) {
            this.setCancelled(true);
            this.response = response;
        }

        public LoginPayloadResponse getResponse() {
            return response;
        }
    }

    public static class Response extends ModernFMLHandshakeEvent {

        private final LoginPayloadResponse response;

        public Response(ProxiedPlayer player, ServerInfo serverInfo, LoginPayloadResponse response) {
            super(player, serverInfo);
            this.response = response;
        }

        public LoginPayloadResponse getResponse() {
            return response;
        }
    }
}
