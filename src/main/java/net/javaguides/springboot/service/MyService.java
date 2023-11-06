package net.javaguides.springboot.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.dto.BOMDTO;

@Service
@Slf4j
public class MyService {
    @Autowired
    private RestTemplate restTemplate;

    public void exportComponentDetails() throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic dGVzdHByb2R1aXQ2M0BnbWFpbC5jb206TWF6ZW5NZWQrMjAyNCsq");

        String url = "https://testproduit.jfrog.io/xray/api/v1/component/exportDetails";

        // Construire le corps de la requête selon la structure donnée
        String requestBody = "{ \"component_name\": \"net.javaguides:springboot-restful-webservices:0.0.1-SNAPSHOT\",\"package_type\":\"maven\", \"cyclonedx\": true,\"cyclonedx_format\": \"json\", \"output_format\":\"json\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Effectuer la requête POST
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);

        // Gérer la réponse, par exemple, l'enregistrer dans un fichier
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            byte[] responseBody = responseEntity.getBody();
            InputStream inputStreamOfResponse = new ByteArrayInputStream(responseBody);
            byte[] jsonByte= extractFileFromZip(inputStreamOfResponse);
           // String texto = new String(jsonByte, StandardCharsets.UTF_8);
            BOMDTO data = objectMapper.readValue(jsonByte, BOMDTO.class);
            log.info(data.getBomFormat());
            log.info(data.getMetadata().getComponent().getName());
            log.info(data.getMetadata().getComponent().getType());
            log.info(data.getMetadata().getComponent().getBomRef());
            log.info(data.getMetadata().getComponent().getVersion());
            log.info(data.getComponents().toString());
            // Spécifiez le chemin du fichier où vous souhaitez enregistrer la réponse
            String filePath = "response.json";

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(jsonByte);
                System.out.println("Response saved to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Request failed with status code: " + responseEntity.getStatusCode());
        }
    }
    public  byte[] extractFileFromZip(InputStream zipInputByte) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(zipInputByte)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                //if (entry.getName().equals(fileName)) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    return outputStream.toByteArray();
                //}
            }
        }
        return null; // Si el archivo no se encuentra en el ZIP
    }
}
