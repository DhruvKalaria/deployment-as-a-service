package com.kuberetes.daas.utils;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.io.IOException;

public class Solution {

	public static void main(String[] args) throws IOException {
		String ipAddr = "35.160.175.116";
		String port = "443";
		String URI = "https://" + ipAddr+ ":" + port;
		Config config = new ConfigBuilder().withMasterUrl(URI)
				.withTrustCerts(true)			          
				.withUsername("admin")
				.withPassword("kjJaRJvARbgIdKCD")
				.build();
		KubernetesClient kubernetesClient = new DefaultKubernetesClient(config);
		System.out.println(kubernetesClient.services().get().getAdditionalProperties());	
		PodList pods = kubernetesClient.pods().list();
		for (Pod pod : pods.getItems()) {
			String name = pod.getMetadata().getName();
			String ip = pod.getStatus().getPodIP();
			System.out.println("Pod: " + name +"Pod IP:"+ ip);
		}
		kubernetesClient.close();
	}
}