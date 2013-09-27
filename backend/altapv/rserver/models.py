from django.db import models

# Create your models here.

class Agente(models.Model):
	numero = models.CharField(max_length=15)
	nombre = models.CharField(max_length=35)
	
	def __unicode__(self):  # Python 3: def __str__(self):
		return self.nombre

class Punto(models.Model):
	TIPO = (
		('0','Despensa'),
		('1','Bodega'),
		('2','Comedor'),
		('3','Otro'),
	)
	id 			= models.IntegerField(primary_key=True)
	agente 		= models.ForeignKey(Agente) 
	nombre 		= models.CharField(max_length=35)
	direccion	= models.CharField(max_length=60, blank=True)
	actividad	= models.CharField(max_length=3, choices=TIPO, blank=True)
	ciudad		= models.CharField(max_length=35, blank=True)
	barrio		= models.CharField(max_length=35, blank=True)
	propietario	= models.CharField(max_length=35)
	telefono	= models.CharField(max_length=20, blank=True)
	ruc			= models.CharField(max_length=20, blank=True)
	latitud		= models.CharField(max_length=30, blank=True)
	longitud	= models.CharField(max_length=30, blank=True)


	def __unicode__(self):  # Python 3: def __str__(self):
		return self.nombre 