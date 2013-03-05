package com.adslur.szt.panel;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.jgoodies.validation.view.ValidationResultViewFactory;


public final class IconFeedbackPanel extends JLayeredPane {

    private static final int CONTENT_LAYER  = 1;
    private static final int FEEDBACK_LAYER = 2;


    private final ValidationResultModel model;


    private final JComponent content;



    public IconFeedbackPanel(ValidationResultModel model, JComponent content) {
        if (model == null) {
            throw new NullPointerException("The validation result model must not be null.");
        }
        if (content == null) {
            throw new NullPointerException("The content must not be null.");
        }

        this.model   = model;
        this.content = content;
        setLayout(new SimpleLayout());
        add(content, CONTENT_LAYER);
        initEventHandling();
    }


   
    public static JComponent getWrappedComponentTree(
            ValidationResultModel model,
            JComponent root) {
        wrapComponentTree(model, root);
        return isScrollPaneWithUnmarkableView(root)
            ? root
            : new IconFeedbackPanel(model, root);
    }

    private static void wrapComponentTree(
            ValidationResultModel model,
            Container container) {
        if (!(container instanceof JScrollPane)) {
            int componentCount = container.getComponentCount();
            for (int i = 0; i < componentCount; i++) {
                Component child = container.getComponent(i);
                if (child instanceof Container) {
                    wrapComponentTree(model, (Container) child);
                }
            }
            return;
        }
        JScrollPane scrollPane = (JScrollPane) container;
        JViewport viewport = scrollPane.getViewport();
        JComponent view = (JComponent) viewport.getView();
        if (isMarkable(view)) {
            return;
        }
        // TODO: Consider adding the following sanity check:
        // the view must not be an IconFeedbackPanel
        Component wrappedView = new IconFeedbackPanel(model, view);
        viewport.setView(wrappedView);
        wrapComponentTree(model, view);
    }

    private static boolean isScrollPaneWithUnmarkableView(Component c) {
        if (!(c instanceof JScrollPane)) {
            return false;
        }
        JScrollPane scrollPane = (JScrollPane) c;
        JViewport viewport = scrollPane.getViewport();
        JComponent view = (JComponent) viewport.getView();
        return !isMarkable(view);
    }


 
    private void initEventHandling() {
        model.addPropertyChangeListener(
                ValidationResultModel.PROPERTYNAME_RESULT,
                new ValidationResultChangeHandler());
    }


  
    private JComponent createFeedbackComponent(
            ValidationResult result,
            Component contentComponent) {
        Icon icon = ValidationResultViewFactory.getSmallIcon(result.getSeverity());
        JLabel label = new JLabel(icon);
        label.setToolTipText(getMessagesToolTipText(result));
        label.setSize(label.getPreferredSize());
        return label;
    }


    private static String getMessagesToolTipText(ValidationResult result) {
        StringBuilder builder = new StringBuilder("<html>");
        for (ValidationMessage message : result.getMessages()) {
            if (builder.length() > 0) {
                builder.append("<br>");
            }
            builder.append(message.formattedText());
        }
        builder.append("</html>");
        return builder.toString();
    }


    private Point getFeedbackComponentOrigin(
            JComponent feedbackComponent,
            Component contentComponent) {
        boolean isLTR = contentComponent.getComponentOrientation().isLeftToRight();
        int x = contentComponent.getX()
              + (isLTR ? 0 : contentComponent.getWidth() - 1)
              - feedbackComponent.getWidth() / 2;
        int y = contentComponent.getY()
              + contentComponent.getHeight()
              - feedbackComponent.getHeight() + 2;

        return new Point(x, y);
    }


  

    private void removeAllFeedbackComponents() {
        int componentCount = getComponentCount();
        for (int i = componentCount - 1; i >= 0; i--) {
            Component child = getComponent(i);
            int layer = getLayer(child);
            if (layer == FEEDBACK_LAYER) {
                remove(i);
            }
        }
    }

   
    private void visitComponentTree(Container container,
            Map<Object, ValidationResult> keyMap, int xOffset, int yOffset) {
        int componentCount = container.getComponentCount();
        for (int i = 0; i < componentCount; i++) {
            Component child = container.getComponent(i);
            if (!child.isVisible()) {
                continue;
            }
            if (isMarkable(child)) {
                if (isScrollPaneView(child)) {
                    Component containerParent = container.getParent();
                    addFeedbackComponent(
                            containerParent,
                            (JComponent) child,
                            keyMap,
                            xOffset - containerParent.getX(),
                            yOffset - containerParent.getY());
                } else {
                    addFeedbackComponent(
                            child,
                            (JComponent) child,
                            keyMap,
                            xOffset,
                            yOffset);
                }
            } else if (isScrollPaneView(child)) {
                // Just do nothing.
            } else if (child instanceof Container) {
                visitComponentTree(
                        (Container) child,
                        keyMap,
                        xOffset + child.getX(),
                        yOffset + child.getY());
            }
        }
    }

    private static boolean isScrollPaneView(Component c) {
        Container container = c.getParent();
        Container containerParent = container.getParent();
        return container       instanceof JViewport
            && containerParent instanceof JScrollPane;
    }


   
    private static boolean isMarkable(Component component) {
        return    component instanceof JTextComponent
               || component instanceof JComboBox;
    }

    private void addFeedbackComponent(
            Component contentComponent,
            JComponent messageComponent,
            Map<Object, ValidationResult> keyMap, int xOffset, int yOffset) {
        ValidationResult result = getAssociatedResult(messageComponent, keyMap);
        JComponent feedbackComponent = createFeedbackComponent(result, contentComponent);
        if (feedbackComponent == null) {
            return;
        }
        add(feedbackComponent, Integer.valueOf(FEEDBACK_LAYER));
        Point overlayPosition = getFeedbackComponentOrigin(feedbackComponent, contentComponent);
        overlayPosition.translate(xOffset, yOffset);
        feedbackComponent.setLocation(overlayPosition);
    }



    private static ValidationResult getAssociatedResult(
            JComponent comp, Map<Object, ValidationResult> keyMap) {
        ValidationResult result = ValidationComponentUtils.getAssociatedResult(comp, keyMap);
        return result == null
            ? ValidationResult.EMPTY
            : result;
    }




    private void updateFeedbackComponents() {
        removeAllFeedbackComponents();
        visitComponentTree(content, model.getResult().keyMap(), 0, 0);
        repaint();
    }


    private void repositionFeedbackComponents() {
        updateFeedbackComponents();
    }



    @Override
    protected void validateTree() {
        super.validateTree();
        if (isVisible()) {
            repositionFeedbackComponents();
        }
        // Validate again, since the removal of the feedback components
        // may have invalidated this compoment.
        super.validateTree();
    }


    private final class ValidationResultChangeHandler implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            updateFeedbackComponents();
        }

    }


    private final class SimpleLayout implements LayoutManager {

     
        public void addLayoutComponent(String name, Component comp) {
            // components are well known by the container
        }

       
        public void removeLayoutComponent(Component comp) {
            // components are well known by the container
        }

       
        public Dimension preferredLayoutSize(Container parent) {
            return content.getPreferredSize();
        }

      
        public Dimension minimumLayoutSize(Container parent) {
            return content.getMinimumSize();
        }

        
        public void layoutContainer(Container parent) {
            Dimension size = parent.getSize();
            content.setBounds(0, 0, size.width, size.height);
        }

    }


}
