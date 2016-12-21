<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ad.css" />
<title>Recibir Mensajes</title>
</head>
<body>
	<f:view>
		<h:form id="ReceptionForm">
			<h:outputLabel value="Tiempo de espera:" for="tiempoEspera" />
			<h:inputText id="tiempoEspera" value="#{beanMensajes.tiempoEspera}" />
			<br />
			<br />
			<h:outputLabel value="Texto Recibido:" for="texto" />
			<h:inputText id="texto" value="#{beanMensajes.texto}">
				<f:validateLength maximum="255" />
			</h:inputText>
			<font color="red"> <h:message for="texto" /></font>
			<br />
			<h:commandButton id="submit"
				actionListener="#{beanMensajes.recibirTexto}" value="Recibir" />
			<br />
			<br />
			<h:selectManyListbox id="mensajesRecibidos"
				value="#{beanMensajes.mensajesRecibidosSeleccionados}"
				styleClass="listAD">
				<f:selectItems value="#{beanMensajes.mensajesRecibidos}" />
			</h:selectManyListbox>
			<br />
			<h:commandButton id="submitTodos"
				actionListener="#{beanMensajes.recibirTodosTexto}"
				value="RecibirTodos" />
			<br />
			<h:commandButton id="volver" action="index" value="Volver" />
			<br />
		</h:form>
	</f:view>
</body>
</html>