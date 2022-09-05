select av.* from availability av
	inner join establishments e on e.code_cnes = av.code_cnes
	inner join addresses a on a.id = e.id_address
	inner join states s on s.id = a.id_state
where s.code = :state
