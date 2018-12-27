//package chc.tfm.udt.convertidores;
//
//import chc.tfm.udt.DTO.ItemDonacion;
//import chc.tfm.udt.DTO.Jugador;
//import chc.tfm.udt.entidades.DonacionEntity;
//import chc.tfm.udt.DTO.Donacion;
//import chc.tfm.udt.entidades.ItemDonacionEntity;
//import chc.tfm.udt.entidades.JugadorEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//// osea que hago un Autowired en cada servicio y en y para trabajar con base de datos las entity y para devolver a la vista los dto
//// te lo haces un bean y lo añades al service para poder llamarlo guay
//// en cada service igual
//// y en los repository lo que persistes son los entity solo
//
//// tienes que ponerle los datos de la entity al new DTO y en el otro al reves
//@Component("DonacionConverter")
//public class DonacionConverterold implements Converter<DonacionEntity, Donacion> {
//
//    @Override
//    public DonacionEntity convertEntity(Donacion dto){
//
//        DonacionEntity e = new DonacionEntity();
//        e.setId(dto.getId());
//        e.setObservacion(dto.getObservacion());
//        e.setDescripcion(dto.getDescripcion());
//        e.setCreateAt(dto.getCreateAt());
//        // Set de lista de items de la factura
//        e.setItems(dto.getItems().
//                stream().
//                map(ItemDonacionEntity::new).
//                collect(Collectors.toList()));
//        // Set del jugador
//        e.setJugadorEntity(new JugadorEntity(dto.getJugador()));
//        return e;
//    }
//
//    @Override
//    public Donacion convertDTO(DonacionEntity e){
//        Donacion d = new Donacion();
//        d.setDescripcion(e.getDescripcion());
//        d.setObservacion(e.getObservacion());
//        d.setCreateAt(e.getCreateAt());
//        d.setId(e.getId());
//        // Seteo de la lista de items de la factura
//        d.setItems(e.getItems().
//                stream().
//                map(ItemDonacion::new).
//                collect(Collectors.toList()));
//        // Set del jugador.
//        d.setJugador(new Jugador(e.getJugadorEntity()));
//
//
//        return d;
//    }
//}
