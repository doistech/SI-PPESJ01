package br.com.doistech.ecclesiasystem.viewmodel

import br.com.doistech.ecclesiasystem.domain.Banner
import org.zkoss.bind.BindContext
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.ContextParam
import org.zkoss.bind.annotation.ContextType
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.image.AImage
import org.zkoss.zk.ui.event.UploadEvent
import org.zkoss.image.Image
import org.zkoss.zul.Label
import org.zkoss.zul.Messagebox
import org.zkoss.util.media.Media;

class BannerVM {
    final String SAVE_PATH = "/Users/brennoagostini/Pictures"
    AImage myImage

    Banner banner
    String tipoBanner
    String urlBanner
    boolean disabledUrl = false
    def tipoBannerList = ['Banner sem link','Banner com link']

    Boolean isErroView = false
    Boolean isErro = false
    Boolean showList = true
    Boolean showForm = false
    def erroList = []

    List<Banner> bannerList = new ArrayList<Banner>()

    @Init
    public void init(){
    }

    @Command("upload")
    @NotifyChange(["*"])
    public void onImageUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
        UploadEvent upEvent
        Object objUploadEvent = ctx.getTriggerEvent()
        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }
        if (upEvent != null) {
            Media media = upEvent.getMedia()
//            int lengthofImage = media.getByteData().length;
            if (media instanceof Image) {
//                if (lengthofImage > 2000 * 2000) {
//                    showInfo("Please Select a Image of size less than 4000Kb.");
//                    return;
//                }
//                else{
                    // media
                    myImage = (AImage) media;  //Initialize the bind object to show image in zul page and Notify it also
//                    File file = new File(SAVE_PATH, media.getName())
                    InputStream inputStream = null
                    OutputStream outputStream = null
                    try {
                        // read this file into InputStream
                        inputStream = media.getStreamData()
                        //new FileInputStream(SAVE_PATH + "/" + media.getStreamData());

                        // write the inputStream to a FileOutputStream
                        outputStream = new FileOutputStream(new File(SAVE_PATH + "/" + media.getName()))
                        urlBanner = SAVE_PATH + "/" + media.getName()
                        int read = 0
                        byte[] bytes = new byte[1024]

                        while ((read = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read)
                        }

                        println "Done!"
                    } catch (Exception e){
                    }

            }else {
                showInfo("The selected File is not an image.");
            }
        }
        else {
            logger.debug("Upload Event Is not Coming");
        }
    }

    public void showInfo(String message) {
        Messagebox.show(message, "Alert !!", Messagebox.OK, Messagebox.INFORMATION);
    }

        // /home/paieternoesaojos/etc/paieternoesaojose.com.br/banner


    @Command
    @NotifyChange(["*"])
    public void save(){
        isErro = false
        erroList.clear()
        erroList.add("Foram encontrados os seguintes erros em seu cadastro:")



        if(banner.nome == null){
            isErro = true
            // mensagemErro == "- O nome é de preenchimento obrigatório <br/>"
            erroList.add("- O nome é de preenchimento obrigatório")
        }
        if(tipoBanner == null){
            isErro = true
            // mensagemErro == "- O nome é de preenchimento obrigatório <br/>"
            erroList.add("- O tipo do banner é de escolha obrigatório")
        }
        if(banner.dataInicio == null){
            isErro = true
            // mensagemErro == "- O nome é de preenchimento obrigatório <br/>"
            erroList.add("- O data de início é de escolha obrigatório")
        }
        if(banner.dataFim == null){
            isErro = true
            // mensagemErro == "- O nome é de preenchimento obrigatório <br/>"
            erroList.add("- O data de fim é de escolha obrigatório")
        }
        if(banner.tipoBanner){
            if(banner.url == null){
                isErro = true
                // mensagemErro == "- O nome é de preenchimento obrigatório <br/>"
                erroList.add("- Por favor digite uma url para o banner")
            }
        }
        if(isErro){
            isErroView = true
        }else{
            banner.save(flush:true)
            // cleanForm()
            // Executions.sendRedirect("/index.zul")
            println 'Salvei o Banner'
        }
    }

    @Command
    @NotifyChange(['*'])
    public void novoBanner(){
        showList = false
        showForm = true
        banner = new Banner()
    }
}
