from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    url(r'^$', 'rserver.views.home', name='home'),
    url(r'^agentes$', 'rserver.views.agentes', name='agentes'),
    url(r'^agentes/add$', 'rserver.views.agenteadd', name='agenteadd'),
    url(r'^puntos$', 'rserver.views.puntos', name='puntos'),
    url(r'^puntos/add$', 'rserver.views.puntoadd', name='puntoadd'),
    # url(r'^altapv/', include('altapv.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    url(r'^admin/', include(admin.site.urls)),
)
