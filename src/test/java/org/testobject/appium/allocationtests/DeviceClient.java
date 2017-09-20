package org.testobject.appium.allocationtests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.testobject.rest.api.model.DeviceDescriptor;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.*;

import static org.testobject.appium.allocationtests.EnvironmentVariables.REST_API;

public class DeviceClient {

	private WebTarget target;
	private List<ExtendedDeviceDescriptor> privateDevices = new ArrayList<>();

	public DeviceClient() {
		X509HostnameVerifier defaultHostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		SslConfigurator sslConfig = SslConfigurator.newInstance();
		LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslConfig.createSSLContext(),
				new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" },
				null,
				defaultHostnameVerifier);

		final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslSocketFactory)
				.build();

		ClientConfig config = new ClientConfig();
		config.property(ApacheClientProperties.CONNECTION_MANAGER, new PoolingHttpClientConnectionManager(registry));
		ApacheConnectorProvider connector = new ApacheConnectorProvider();
		config.connectorProvider(connector);
		config.register(MultiPartFeature.class);
		config.register(JacksonFeature.class);
		Client client = ClientBuilder.newBuilder().newClient(config);
		target = client.target(REST_API);
	}

	public DeviceClient login(String userName, String password) {
		MultivaluedMap<String, String> formData = new MultivaluedStringMap();
		formData.add("user", userName);
		formData.add("password", password);

		Response response = target
				.path("users").path("login")
				.request()
				.post(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED));

		if (Response.Status.OK.getStatusCode() != response.getStatus()) {
			throw new IllegalStateException("expected status " + Response.Status.OK + " but was " + response.getStatus());
		}
		return this;
	}

	private void getPrivateDevices() {
		DataCenters dcs = target.path("multiDcDescriptors").request(MediaType.APPLICATION_JSON_TYPE).get(DataCenters.class);
		dcs.dataCenters.forEach((name, descriptors) -> {
			descriptors.stream().filter(descriptor -> descriptor.isPrivate).forEach(descriptor -> {
				privateDevices.add(descriptor);
			});
		});
	}

	public boolean isPrivate(String deviceName) {
		getPrivateDevices();
		return !privateDevices.isEmpty() && privateDevices.stream()
				.anyMatch(device -> Objects.equals(device.id, deviceName));
	}

	static class DataCenters {
		final Map<String, List<ExtendedDeviceDescriptor>> dataCenters = new HashMap<>();

		DataCenters(@JsonProperty("EU") List<ExtendedDeviceDescriptor> eu, @JsonProperty("US") List<ExtendedDeviceDescriptor> us) {
			this.dataCenters.put("eu", eu);
			this.dataCenters.put("us", us);
		}
	}

	static class ExtendedDeviceDescriptor extends DeviceDescriptor.DeviceContainer {
		boolean isPrivate;

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonCreator
		public ExtendedDeviceDescriptor(@JsonProperty("isPrivate") Boolean isPrivate,
				@JsonProperty("id") String id,
				@JsonProperty("name") String name,
				@JsonProperty("apiLevel") int apiLevel,
				@JsonProperty("os") DeviceDescriptor.OS os,
				@JsonProperty("osVersion") String osVersion) {

			this.isPrivate = isPrivate;
			super.id = id;
			super.name = name;
			super.apiLevel = apiLevel;
			super.os = os;
			super.osVersion = osVersion;
		}

		@Override public String toString() {
			return String.format(" id %s , name %s", id, name);
		}
	}
}
