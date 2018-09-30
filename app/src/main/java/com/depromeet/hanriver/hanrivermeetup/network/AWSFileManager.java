package com.depromeet.hanriver.hanrivermeetup.network;

import android.content.Context;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.depromeet.hanriver.hanrivermeetup.BuildConfig;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TimelineFragment;

import java.io.File;

public class AWSFileManager {
    public static void uploadImage(TimelineFragment parentFragment, Context context, String fileName, File file) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(BuildConfig.AwsAccessKey, BuildConfig.AwsSecretKey);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);

        TransferUtility transferUtility =
                TransferUtility.builder()
                        .context(context)
                        .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                        .s3Client(s3Client)
                        .build();

        TransferObserver uploadObserver =
                transferUtility.upload("posts/" + fileName, file);

        uploadObserver.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                if (TransferState.COMPLETED == state) {
                    parentFragment.onResume();
                    Toast.makeText(context,
                            "포스팅 완료", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                int percentDone = (int) percentDonef;
            }

            @Override
            public void onError(int id, Exception ex) {
                // Handle errors
            }

        });
    }
}
