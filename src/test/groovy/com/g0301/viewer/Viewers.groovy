package com.g0301.viewer

import com.g0301.gui.LanternaGUI
import com.g0301.model.Button
import com.g0301.model.Car
import com.g0301.model.Player
import com.g0301.model.Portal
import com.g0301.model.Position
import com.g0301.model.Trail
import com.g0301.model.Wall
import spock.lang.Specification

class Viewers extends Specification {

    LanternaGUI gui;

    def setup() {
        gui = Mock()
    }

    def "test car viewer"() {
        given:
            Car car = new Player(new Position(20, 20), "#FFFFFF")
            CarViewer carViewer = new CarViewer()
        when:
            carViewer.drawElement(car, gui)
        then:
            1 * gui.drawCar(car.getPosition(), "#FFFFFF")
    }

    def "test button viewer"() {
        given:
            Button button = new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 10, 10)
            ButtonViewer buttonViewer = new ButtonViewer()
        when:
            buttonViewer.drawElement(button, gui)
        then:
            1 * gui.drawButton(button.getPosition(), button.getColor() ,button.getTextColor(), button.getText(), button.getWidth(), button.getHeight())
    }

    def "test portal viewer"() {
        given:
            Portal portal = new Portal(new Position(10, 10), new Position(20, 20), "#FFFFFF")
            PortalViewer portalViewer = new PortalViewer()
        when:
            portalViewer.drawElement(portal, gui)
        then:
            1 * gui.drawPortal(portal.getPosition(), portal.getSecondPosition(), portal.getColor())
    }

    def "test trail viewer"() {
        given:
            TrailViewer trailViewer = new TrailViewer()
            Trail trail = new Trail(new Position(10, 10), "#FFFFFF")
        when:
            trailViewer.drawElement(trail, gui)
        then:
            1 * gui.drawTrail(trail.getPosition(), trail.getColor())
    }

    def "test wall viewer"() {
        given:
            Wall wall = new Wall(new Position(10, 10), "#FFFFFF")
            WallViewer wallViewer = new WallViewer()
        when:
            wallViewer.drawElement(wall, gui)
        then:
            1 * gui.drawWall(wall.getPosition(), wall.getColor())
    }

    def "test boost bar viewer"() {
        given:
            Trail trail = new Trail(new Position(1, 1), "#FFFFFF")
            Player1BoostBarViewer boostBarViewer = new Player1BoostBarViewer()
        when:
            boostBarViewer.drawElement(trail, gui)
        then:
            1 * gui.drawPlayer1BoostBar(trail.getPosition(), "#FFFFFF")
    }
}
