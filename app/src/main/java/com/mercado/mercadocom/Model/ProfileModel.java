package com.mercado.mercadocom.Model;

public class ProfileModel {
    private String ProfileImage;
    private String UploadId;

    public ProfileModel()
    {
    }

    public ProfileModel(String profileImage, String uploadId) {
        ProfileImage = profileImage;
        UploadId = uploadId;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }


    public String getUploadId() {
        return UploadId;
    }

    public void setUploadId(String uploadId) {
        UploadId = uploadId;
    }
}
