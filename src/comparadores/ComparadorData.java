package comparadores;

import java.util.Comparator;

import controladores.Doacao;
public class ComparadorData implements Comparator<Doacao>{

	@Override
	public int compare(Doacao o1, Doacao o2) {
		if (o1.getData().compareTo(o2.getData()) > 0) {
			return 1;
		} else if (o1.getData().compareTo(o2.getData()) < 0) {
			return -1;
		} else {
			return o1.getDescritor().compareTo(o2.getDescritor());
		}
	}

}
