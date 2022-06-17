public class Calculo {
    private String estoque;
    public void dobrar(){
        int num = Integer.parseInt(getEstoque());
        String n = String.valueOf(2*num);
        setEstoque(n);
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque.replaceAll("\\D+",""); //Retirando os chars da String
    }
}
