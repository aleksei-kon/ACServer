package by.aleksei.acs.restful

import by.aleksei.acs.Constants.Image.ATTACHMENT_FILE
import by.aleksei.acs.Constants.Image.DEFAULT_PATH
import by.aleksei.acs.service.HeaderService
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.stereotype.Component
import java.io.*
import java.sql.Timestamp
import javax.ws.rs.*
import javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("image/")
class ImageResource(private val headerService: HeaderService) : BaseResource() {

    @GET
    @Path("download/{fileName}")
    @Produces("image/png", "image/jpg", "image/gif")
    fun downloadImage(@PathParam("fileName") fileName: String): Response {
        val file = File("$DEFAULT_PATH$fileName")

        return Response
                .ok(file as Any)
                .header(CONTENT_DISPOSITION, "$ATTACHMENT_FILE=\"$fileName\"")
                .build()
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    fun uploadImage(
            @FormDataParam("uploadFile") inputStream: InputStream,
            @FormDataParam("uploadFile") form: FormDataContentDisposition
    ): Response {
        val fileName = form.fileName
        val uploadFilePath = writeToFileStorage(inputStream, fileName)

        return Response
                .ok(uploadFilePath)
                .build()
    }

    private fun writeToFileStorage(inputStream: InputStream, fileName: String): String {
        var outputStream: OutputStream? = null
        val newFileName = "${Timestamp(System.currentTimeMillis()).time}$fileName"

        try {
            outputStream = FileOutputStream(File("$DEFAULT_PATH$newFileName"))
            var read = 0
            val bytes = ByteArray(1024)
            read = inputStream.read(bytes)
            while (read != -1) {
                outputStream.write(bytes, 0, read)
                read = inputStream.read(bytes)
            }
            outputStream.flush()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        } finally {
            outputStream?.close()
        }

        return newFileName
    }
}