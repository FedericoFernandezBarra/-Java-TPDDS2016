package ar.edu.dds.tpa.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class HorarioDeAtencion {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "HorariosDeAtencionPorDia", inverseJoinColumns = @JoinColumn(name = "DiaYHorarioDeAtencion_id"))
	private Set<DiaYHorarioDeAtencion> horariosDeAtencionPorDia;

	public HorarioDeAtencion() {
		horariosDeAtencionPorDia = new HashSet<DiaYHorarioDeAtencion>();
	}

	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		if (horariosDeAtencionPorDia.stream()
				.anyMatch(unDiaYHorarioDeAtencion -> unDiaYHorarioDeAtencion.getDia().equals(unDia))) {
			horariosDeAtencionPorDia.stream()
					.filter(unDiaYHorarioDeAtencion -> unDiaYHorarioDeAtencion.getDia().equals(unDia))
					.forEach(unDiaYHorarioDeAtencion -> unDiaYHorarioDeAtencion.agregarHorario(horarioDesde,
							horarioHasta));
		} else {
			horariosDeAtencionPorDia.add(new DiaYHorarioDeAtencion(unDia));
			agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta);
		}
	}

	public void agregarHorarioDeAtencion(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		dias.stream().forEach(unDia -> agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta));
	}

	public boolean seAtiendeEn(LocalDateTime unDiaYHorario) {
		return horariosDeAtencionPorDia.stream().filter(
				unDiaYHorarioDeAtencion -> unDiaYHorarioDeAtencion.getDia().equals(unDiaYHorario.getDayOfWeek()))
				.anyMatch(unDiaYHorarioDeAtencion -> unDiaYHorarioDeAtencion.seAtiendeEn(unDiaYHorario));
	}
}