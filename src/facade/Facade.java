package facade;
import easyaccept.EasyAccept;

public class Facade {

    public static void main(String[] args) {
        args = new String[] {"facade.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt" , "testes_aceitacao/use_case_3.txt"};
        EasyAccept.main(args);
    }
    
    public void adicionaDoador(String id, String nome, String email, String celular, String classe){

    }

    public String pesquisaUsuarioPorId(String id){
        return "";
    }

    public String pesquisaUsuarioPorNome(String nome){
        return "";
    }

    //TODO METODO ATUALIZA USUARIO

    public void removeUsuario(String id){

    }

    public void adicionaDescritor(String descricao){

    }

    public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags){

    }

    public String exibeItem(String idItem, String idDoador){
        return "";
    }


    //TODO METODO ATUALIZAR ITEM


    public void removeItemParaDoacao(String idItem, String idDoador){

    }

    public String listaDescritorDeItensParaDoacao(){
        return "";
    }

    public String listaItensParaDoacao(){
        return "";
    }

    public String pesquisaItemParaDoacaoPorDescricao(String desc){
        return "";
    }



}
