// package com.drl.drlwebsite.service;

// import com.cloudinary.Cloudinary;
// import com.cloudinary.utils.ObjectUtils;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.Map;

// @Service
// public class CloudinaryService {

//     @Autowired
//     private Cloudinary cloudinary;

//     // ✅ UPLOAD (same as yours)
//     public String uploadFile(MultipartFile file) {
//         try {
//             Map uploadResult = cloudinary.uploader().upload(
//                     file.getBytes(),
//                     ObjectUtils.asMap("resource_type", "auto")
//             );

//             return uploadResult.get("secure_url").toString();

//         } catch (Exception e) {
//             throw new RuntimeException("Upload failed: " + e.getMessage());
//         }
//     }

//     // 🔥 DELETE (NEW)
//     public void deleteFile(String fileUrl) {

//         if (fileUrl == null || fileUrl.isEmpty()) return;

//         try {
//             String publicId = fileUrl
//                     .substring(fileUrl.indexOf("/upload/") + 8)
//                     .replaceAll("v\\d+/", "")
//                     .replaceAll("\\.(jpg|jpeg|png|webp|pdf)$", "");

//             // detect type
//             if (fileUrl.endsWith(".pdf")) {
//                 cloudinary.uploader().destroy(
//                         publicId,
//                         ObjectUtils.asMap("resource_type", "raw")
//                 );
//             } else {
//                 cloudinary.uploader().destroy(
//                         publicId,
//                         ObjectUtils.emptyMap()
//                 );
//             }

//         } catch (Exception e) {
//             System.out.println("Cloudinary delete failed: " + e.getMessage());
//         }
//     }
// }

package com.drl.drlwebsite.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    // ✅ FIXED UPLOAD (IMPORTANT)
    public String uploadFile(MultipartFile file) {
        try {

            String contentType = file.getContentType();

            Map uploadResult;

            // 🔥 HANDLE PDF CORRECTLY
            if (contentType != null && contentType.equals("application/pdf")) {

                uploadResult = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap("resource_type", "raw")
                );

            } else {

                uploadResult = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap("resource_type", "image")
                );
            }

            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }

    // ✅ DELETE (WORKS FOR BOTH IMAGE + PDF)
    public void deleteFile(String fileUrl) {

        if (fileUrl == null || fileUrl.isEmpty()) return;

        try {

            String publicId = fileUrl
                    .substring(fileUrl.indexOf("/upload/") + 8)
                    .replaceAll("v\\d+/", "")
                    .replaceAll("\\.(jpg|jpeg|png|webp|pdf)$", "");

            // 🔥 DELETE BASED ON TYPE
            if (fileUrl.contains("/raw/upload/")) {

                cloudinary.uploader().destroy(
                        publicId,
                        ObjectUtils.asMap("resource_type", "raw")
                );

            } else {

                cloudinary.uploader().destroy(
                        publicId,
                        ObjectUtils.emptyMap()
                );
            }

        } catch (Exception e) {
            System.out.println("Cloudinary delete failed: " + e.getMessage());
        }
    }
}