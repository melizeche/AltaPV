# -*-coding=utf-8 -*- 
from django.contrib import admin
from rserver.models import Agente, Punto

#class AgenteAdmin(admin.ModelAdmin):
#	fields = ['nombre','numero']
class PuntoAdmin(admin.ModelAdmin):
	fieldsets = [
		('Datos Obligatorios', {'fields': ['agente','nombre','actividad','propietario']}),
		('Ubicación', {'fields': ['direccion','barrio','ciudad','latitud','longitud']}),
		('Mas información', {'fields': ['telefono','ruc','id']})
			]
	list_display = ('nombre','ciudad','agente','actividad')
	list_filter = ['agente']
	search_fields = ['nombre']

admin.site.register(Agente)#, AgenteAdmin)
admin.site.register(Punto, PuntoAdmin)