INSERT INTO diferencias ('referencia', 'descripcion', 'contado', 'teorico', 'diferencia')
	select capturas.codigoInterno, 
		baseProductos.descripcion, 
		SUM(capturas.cantidad_capturada), 
		stock_teorico.stock_teorico, 
		SUM(capturas.cantidad_capturada) - stock_teorico.stock_teorico
	FROM capturas, stock_teorico, baseProductos
	WHERE capturas.codigoInterno = stock_teorico.codigoInterno 
		AND capturas.codigoInterno = baseProductos.codigoInterno
	group by capturas.codigoInterno
	UNION ALL
	SELECT stock_teorico.codigoInterno, 
		baseProductos.descripcion, 
		0, 
		stock_teorico.stock_teorico, 
		0-stock_teorico.stock_teorico
	FROM stock_teorico, capturas, baseProductos
	where stock_teorico.codigoInterno NOT IN (
	SELECT codigoInterno FROM capturas) 
		AND stock_teorico.codigoInterno = baseProductos.codigoInterno 
	GROUP BY stock_teorico.codigoInterno