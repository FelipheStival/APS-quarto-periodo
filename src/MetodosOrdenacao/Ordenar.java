package MetodosOrdenacao;

import java.util.ArrayList;
import Banco.FuncoesBanco;
import Banco.InfoImagem;

public class Ordenar {

	public ArrayList<InfoImagem> SelectionSort(ArrayList<InfoImagem> Imagens) {
		for (int i = 0; i < Imagens.size() - 1; i++) {
			int minElementIndex = i;
			for (int j = i + 1; j < Imagens.size(); j++) {
				if (Imagens.get(minElementIndex).getTamanho() > Imagens.get(j).getTamanho()) {
					minElementIndex = j;
				}
			}
			if (minElementIndex != i) {
				InfoImagem temp = Imagens.get(i);
				Imagens.set(i, Imagens.get(minElementIndex));
				Imagens.set(minElementIndex, temp);
			}
		}

		return Imagens;
	}

	public ArrayList<InfoImagem> BubbleShort(ArrayList<InfoImagem> Imagens) {
		for (int i = Imagens.size(); i >= 1; i--) {
			for (int j = 1; j < i; j++) {
				if (Imagens.get(j - 1).getTamanho() > Imagens.get(j).getTamanho()) {
					InfoImagem Auxiliar = Imagens.get(j);
					Imagens.set(j, Imagens.get(j - 1));
					Imagens.set(j - 1, Auxiliar);
				}
			}
		}
		return Imagens;
	}

	public ArrayList<InfoImagem> InsertionShot(ArrayList<InfoImagem> Imagens) {
		int j;
		InfoImagem key;
		int i;

		for (j = 1; j < Imagens.size(); j++) {
			key = Imagens.get(j);
			for (i = j - 1; (i >= 0) && (Imagens.get(i).getTamanho() > key.getTamanho()); i--) {
				Imagens.set(i + 1, Imagens.get(i));
			}
			Imagens.set(i + 1, key);
		}
		return Imagens;
	}
}
