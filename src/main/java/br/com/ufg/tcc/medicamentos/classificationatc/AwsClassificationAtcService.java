package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import br.com.ufg.tcc.medicamentos.config.AwsCredentialsConfig;
import br.com.ufg.tcc.medicamentos.medicament.MedicamentEntity;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AwsClassificationAtcService {

    @Autowired
    private AwsCredentialsConfig awsCredentialsConfig;

    public void sendClassifications(List<ClassificacionAtc> classifications) {
        AmazonS3 s3client = buildClient();

        File file = new File("send-data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, classifications);
            s3client.putObject(
                    new PutObjectRequest(
                            awsCredentialsConfig.getBucketName(),
                            "classifications.json",
                            file).withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            file.delete();
        }


    }

    public void sendMedicaments(List<MedicamentEntity> medicaments) {
        AmazonS3 s3client = buildClient();

        File file = new File("send-data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, medicaments);
            s3client.putObject(
                    new PutObjectRequest(
                            awsCredentialsConfig.getBucketName(),
                            "medicaments.json",
                            file).withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            file.delete();
        }

    }


    private AmazonS3 buildClient() {
        AWSCredentials credentials = new BasicAWSCredentials(
                awsCredentialsConfig.getAccessKey(),
                awsCredentialsConfig.getSecretKey()
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();

        if(!s3client.doesBucketExist(awsCredentialsConfig.getBucketName())) {
            s3client.createBucket(awsCredentialsConfig.getBucketName());
        }

        return s3client;
    }


}
