public class ArvorePatricia {
    private static abstract class PatNo { }
    
    private static class PatNoInt extends PatNo {
        int index;
        PatNo esq, dir ;
    }

    private static class PatNoExt extends PatNo {
        String palavra;
    }

    private PatNo raiz;
    
    private String getLetraBinario(int letra) {
        String letraBinario = Integer.toBinaryString(letra);
        
        if(letraBinario.length() < 8) {
            int dif = 8 - letraBinario.length();
            for(int i = 0; i < dif; i++) {
                letraBinario = "0" + letraBinario;
            }
        }

        return letraBinario;
    }

    private String getPalavraBinario(String palavra) {
        String palavraBinaria = "";

        for(int j = 0; j < palavra.length(); j++) {
            int letraInteiro = (int)palavra.charAt(j);
            String letraBinario = this.getLetraBinario(letraInteiro);
            palavraBinaria += letraBinario;
        }

        if(palavraBinaria.length() < 128) {
            int dif = 128 - palavraBinaria.length();
            for(int j = 0; j < dif; j++)
                palavraBinaria = "0" + palavraBinaria;
        }

        return palavraBinaria;
    }

    // Retorna o i-ésimo bit da chave k a partir da esquerda
    private int bit(int i , String k) {
        if (i == 0) 
            return 0;

        String palavraBinaria = this.getPalavraBinario(k);
        int bitNeeded = Character.getNumericValue(palavraBinaria.charAt(i));
        return bitNeeded;
    }

    // Verifica se p é nó externo
    private boolean eExterno (PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    //Método para criar nó interno:
    private PatNo criaNoInt (int i , PatNo esq, PatNo dir ) {
        PatNoInt p = new PatNoInt ();
        p.index = i ; 
        p.esq = esq; 
        p. dir = dir ;
        return p;
    }

    //Método para criar nó externo:
    private PatNo criaNoExt (String k) {
        PatNoExt p = new PatNoExt();
        p.palavra = k;
        return p;
    }

    //Método para pesquisa:
    private void pesquisa (String k, PatNo t ) {
        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.palavra.equals(k)) 
                System.out. println ( "Elemento encontrado" );
            else 
                System.out. println ( "Elemento nao encontrado" );
        }
        else {
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) 
                pesquisa (k, aux.esq);
            else
                pesquisa (k, aux. dir );
        }
    }
    
    private PatNo insereEntre (String k, PatNo t , int i) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) 
            aux = (PatNoInt) t;
        if (this.eExterno(t) || (i < aux.index) ) { 
            PatNo p = this.criaNoExt(k);
            if (this.bit(i , k) == 1)
                return this. criaNoInt ( i , t , p);
            else 
                return this. criaNoInt ( i , p, t );   
        } else {
            if (this.bit(aux.index , k) == 1)
                aux. dir = this.insereEntre (k, aux. dir , i );
            else 
                aux. esq = this.insereEntre (k, aux.esq, i );
            return aux;
        }
    }

    private PatNo insere (String k, PatNo t) {
        if ( t == null) 
            return this.criaNoExt(k);
        else {
            PatNo p = t;
            while (!this.eExterno(p)) {
                PatNoInt aux = (PatNoInt)p;
                if (this.bit(aux.index, k) == 1) 
                    p = aux.dir ;
                else 
                    p = aux.esq;
            }
            PatNoExt aux = (PatNoExt)p;
            int i = 1; // acha o primeiro bit diferente
            int numeroBits = 128;
            while (( i < numeroBits) && (this.bit(i , k) == this.bit(i, aux.palavra))) 
                i++;
            if (i >= numeroBits) {
                return t;
            }
            else 
                return this.insereEntre(k, t , i);
        }
    }


    public ArvorePatricia () {
        this.raiz = null;
    }

    public void pesquisa (String k){ this.pesquisa(k, this.raiz );}
    public void insere (String k){ this.raiz = this.insere (k, this.raiz );}
}

