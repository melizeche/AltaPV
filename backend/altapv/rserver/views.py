 # Create your views here.
from django.http import HttpResponse, HttpResponseRedirect, HttpRequest
from django.template.loader import get_template
from django.template import Context
from datetime import datetime
from django.shortcuts import render_to_response

#from altapv.forms import *
from django.template.context import RequestContext
from rserver.models import *

from django import forms

class AgenteForm(forms.Form):
    numero = forms.CharField(max_length=15)
    nombre = forms.CharField(max_length=35)


class PuntoForm(forms.Form):
	TIPO = (
		('0','Despensa'),
		('1','Bodega'),
		('2','Comedor'),
		('3','Otro'),
	)
	agente 		= forms.CharField(max_length=20)
	nombre 		= forms.CharField(max_length=35)
	direccion	= forms.CharField(max_length=60)
	actividad	= forms.ChoiceField(choices=TIPO)
	ciudad		= forms.CharField(max_length=35)
	barrio		= forms.CharField(max_length=35)
	propietario	= forms.CharField(max_length=35)
	telefono	= forms.CharField(max_length=20)
	ruc			= forms.CharField(max_length=20)
	latitud		= forms.CharField(max_length=30)
	longitud	= forms.CharField(max_length=30)
    

def home(request):
	
	return HttpResponse("Hello, world. You're at the index.")
	
def agentes(request):
	todos = Agente.objects.all().order_by('nombre')
	output = ', '.join([p.nombre for p in todos])
	return render_to_response('agentes/index.html', {'todos': todos})

	#return HttpResponse("Hello, world. You're at the poll index.")

def agenteadd(request):
	print "1"
	if request.method == 'POST': # If the form has been submitted...
		
		form = AgenteForm(request.POST) # A form bound to the POST data
		if form.is_valid(): # All validation rules pass    
			#print form
			nom =  request.POST['nombre']
			num = request.POST['numero']
			
			if not 'flag' in request.POST:
				a = Agente(numero=num, nombre=nom)
				a.save()
			#if request.
			return HttpResponse('valido <br>' + nom + ' = ' + num) 
		else:
			return HttpResponse('no valido')
	else:
		form = AgenteForm()
		return render_to_response('agentes/add.html', {'form': form})	

def puntoadd(request):
	
	if request.method == 'POST': # If the form has been submitted...
		form = PuntoForm(request.POST) # A form bound to the POST data
		print request.POST
		if form.is_valid(): # All validation rules pass    
			ag = Agente.objects.get(numero=request.POST['agente'])
			nom =  request.POST['nombre']
			num = request.POST['telefono']
			dire = request.POST['direccion']
			acti = request.POST['actividad']
			ciud = request.POST['ciudad']
			prop = request.POST['propietario']
			lat = request.POST['latitud']
			log = request.POST['longitud']
			ru = request.POST['ruc']
			bar = request.POST['barrio']

			print 'si'
			if not 'flag' in request.POST:
				print 'siiii'
				p = Punto(agente=ag, nombre=nom, direccion=dire, telefono=num, actividad=acti, ciudad=ciud, propietario=prop, latitud=lat, longitud=log, ruc=ru, barrio=bar)
				p.save()
			#if request.
			print "valido"
			return HttpResponse('valido <br>' + nom + ' = ' + num) 
			
		else:
			print 'no valido'
			return HttpResponse('no valido')

	else:
		form = PuntoForm()
		return render_to_response('agentes/add.html', {'form': form})	

def puntos(request):
    return HttpResponse("Hello, world. You're at the puntos.")

def mapa(request, valor):
	lat = Punto.objects.get(id=valor)
	return render_to_response('puntos/maps.html', {'nombre': lat.nombre, 'latitud': lat.latitud, 'longitud': lat.longitud})
	#return HttpResponse("Hello, world. You're at the puntos. " + valor)