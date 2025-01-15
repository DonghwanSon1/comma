package com.project.comma.domain.image

import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.UUID
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/image")
@Tag(name = "Image", description = "이미지 관련 API")
class ImageController(
    @Value("\${file.dir}")
    private val uploadDir: String,

    @Value("\${file.imageUrl}")
    private val serverUrl: String
) {

    @PostMapping("/upload")
    @Operation(summary = "이미지 업로드", description = "이미지를 업로드 한다.")
    fun uploadImage(@RequestParam("file") file: MultipartFile): String {
        if (file.isEmpty) throw CommonException(CommonExceptionCode.IMAGE_BAD_REQUEST)
        val uploadPath = Paths.get(uploadDir)
        val imageName: String = UUID.randomUUID().toString()

        val targetLocation = uploadPath.resolve(imageName)
        file.inputStream.use { inputStream ->
            Files.copy(inputStream, targetLocation)
        }

        val imageUrl = "$serverUrl/$imageName"

        return imageUrl
    }

    @GetMapping("/{imageName}")
    @Operation(summary = "이미지 조회", description = "이미지를 조회 한다.")
    fun getImage(@PathVariable imageName: String, response: HttpServletResponse) {
        val imageFile = File(uploadDir, imageName)

        if (!imageFile.exists()) throw CommonException(CommonExceptionCode.NO_IMAGE_EXISTS)

        response.contentType = "image/jpeg"
        response.outputStream.use { out ->
            imageFile.inputStream().use { inputStream ->
                inputStream.copyTo(out)
            }
        }
    }

    @DeleteMapping("/{imageName}")
    @Operation(summary = "이미지 삭제", description = "이미지를 삭제 한다.")
    fun deleteImage(@PathVariable imageName: String) {
        val path: Path = Paths.get("$uploadDir/$imageName")

        if (!Files.exists(path)) throw CommonException(CommonExceptionCode.NO_IMAGE_EXISTS)
        Files.delete(path)
    }

}