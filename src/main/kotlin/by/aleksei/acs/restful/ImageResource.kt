package by.aleksei.acs.restful

import by.aleksei.acs.Constants.Image.ATTACHMENT_FILE
import by.aleksei.acs.Constants.Image.DEFAULT_PATH
import by.aleksei.acs.service.HeaderService
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.stereotype.Component
import java.io.*
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("image/")
class ImageResource(private val headerService: HeaderService) : BaseResource() {

    @GET
    @Path("download/{fileName}")
    @Produces("image/png", "image/jpg", "image/gif")
    fun downloadImage(@Context headers: HttpHeaders,
                      @PathParam("fileName") fileName: String): Response {
        val file = File("$DEFAULT_PATH$fileName")

        return Response
                .ok(file as Any)
                .header(CONTENT_DISPOSITION, "$ATTACHMENT_FILE=\"$fileName\"")
                .build()
    }
    //http://192.168.100.38:8080/image/download/image1.jpg

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun uploadImage(
            @Context headers: HttpHeaders,
            @FormDataParam("uploadFile") fileInputStream: InputStream,
            @FormDataParam("uploadFile") fileFormDataContentDisposition: FormDataContentDisposition
    ): Response {
        val fileName = fileFormDataContentDisposition.fileName
        val uploadFilePath = writeToFileServer(fileInputStream, fileName)

        return Response.ok(uploadFilePath).build()
    }

    private fun writeToFileServer(inputStream: InputStream, fileName: String): String {
        var outputStream: OutputStream? = null
        val qualifiedUploadFilePath = "$DEFAULT_PATH$fileName"

        try {
            outputStream = FileOutputStream(File(qualifiedUploadFilePath))
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

        return qualifiedUploadFilePath
    }

}